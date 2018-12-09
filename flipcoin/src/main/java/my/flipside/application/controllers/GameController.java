package my.flipside.application.controllers;


import my.flipside.application.model.FlipGame;
import my.flipside.application.model.FlipResult;
import my.flipside.application.model.FlipStat;
import my.flipside.application.model.FlipUser;
import my.flipside.application.service.GameService;
import my.flipside.application.service.ResultService;
import my.flipside.application.service.StatService;
import my.flipside.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
@RequestMapping("/game")
@EnableTransactionManagement
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class GameController {

    @Autowired
    UserService userService;
    @Autowired
    GameService gameService;
    @Autowired
    StatService statService;
    @Autowired
    ResultService resultService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loadGamePage(HttpSession session, ModelAndView view, @RequestParam String gameId) {
        FlipGame game = (FlipGame) session.getAttribute("currentGame");
        if (game != null) {
            game = gameService.getGame(game.getGameId());
            FlipResult result = resultService.getResultByGameId(game.getGameId());
            if (result != null) {
                session.removeAttribute("currentGame");
                session.removeAttribute("creator");
                view.addObject("result", result)
                        .setViewName("game");
                return view;
            } else {
                session.setAttribute("currentGame", game);
                view.addObject("game", game)
                        .setViewName("game");
                return view;
            }
        } else {
            Integer id = Integer.parseInt(gameId);
            FlipGame newGame = enterGameById(id, userService.getUser(((FlipUser) session.getAttribute("user")).getUserId()));
            if (newGame != null) {
                session.setAttribute("currentGame", newGame);
                view.setViewName("main");
                return view;
            } else {
                view.addObject("error", "Could not join room!")
                        .setViewName("main");
                return view;
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public FlipGame enterGameById(Integer gameId, FlipUser user) {
        FlipGame game = gameService.getGame(gameId);
        if (game != null && user.getStat().getAccount() >= game.getBet() && !game.isCompleted()) {
            if (game.getJedi() == null) {
                game.setJedi(user);
            } else if (game.getSith() == null) {
                game.setSith(user);
            }
            return gameService.updateGame(game);
        } else {
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ModelAndView flipSide(@ModelAttribute FlipGame game, ModelAndView view, HttpSession session) {
        Boolean side = new Random().nextBoolean();
        game = gameService.getGame(game.getGameId());
        Boolean result;
        if (side) {
            result = commitGameResult(side, game.getJedi(), game.getSith(), game.getBet());
        } else {
            result = commitGameResult(side, game.getSith(), game.getJedi(), game.getBet());
        }

        session.removeAttribute("currentGame");
        session.removeAttribute("creator");

        if (!result) {
            gameService.deleteGame(game.getGameId());
            view.addObject("error", "Something goes wrong, game is not accounted!")
                    .setViewName("main");
            return view;
        }

        game.setCompleted(true);
        gameService.updateGame(game);
        view.addObject("resultCoin",
                side ? "https://www.digitaltrends.com/wp-content/uploads/2011/08/star-wars-coins-2.png"
                        : "https://laughingsquid.com/wp-content/uploads/darth.png");
        view.addObject("result", resultService.createResult(
                new FlipResult(game, (side ? game.getJedi() : game.getSith()))))
                .setViewName("game");
        return view;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean commitGameResult(Boolean side, FlipUser winner, FlipUser looser, Integer bet) {
        FlipStat looserStat = looser.getStat();
        looserStat.setAccount(looserStat.getAccount() - bet);

        FlipStat winnerStat = winner.getStat();
        if (side) {
            winnerStat.setScore(winnerStat.getScore() + 1);
        } else {
            winnerStat.setScore(winnerStat.getScore() - 1);
        }
        winnerStat.setAccount(winnerStat.getAccount() + bet);

        return (statService.updateStat(looserStat) != null && statService.updateStat(winnerStat) != null);
    }
}
