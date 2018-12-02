package my.flipside.application.controllers;


import my.flipside.application.model.FlipGame;
import my.flipside.application.model.FlipResult;
import my.flipside.application.model.FlipStat;
import my.flipside.application.model.FlipUser;
import my.flipside.application.service.GameService;
import my.flipside.application.service.ResultService;
import my.flipside.application.service.StatService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller
@RequestMapping("/game")
@EnableTransactionManagement
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class GameController {

    @Autowired
    GameService gameService;
    @Autowired
    StatService statService;
    @Autowired
    ResultService resultService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loadGamePage(HttpServletRequest request, ModelAndView view, @RequestParam(required = false) String gameId) {
        FlipGame game = (FlipGame) request.getSession(false).getAttribute("currentGame");
        if (game == null) {
            view.addObject("error", "There is no game you participating in!");
            view.setViewName("main");
            return view;
        }
        if (gameId != null) {
            Integer id = Integer.parseInt(gameId);
            game = enterGameById(id, (FlipUser) request.getSession(false).getAttribute("user"));
        }
        if (game == null) {
            view.addObject("error", "Could not join room!");
            view.setViewName("main");
            return view;
        } else {
            request.getSession(false).setAttribute("currentGame", gameService.getGame(game.getGameId()));
            view.addObject("game", gameService.getGame(game.getGameId()));
            view.setViewName("game");
            return view;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public FlipGame enterGameById(Integer gameId, FlipUser user) {
        FlipGame game = gameService.getGame(gameId);
        if (game != null && user.getStat().getAccount() >= game.getBet()) {
            if (game.getJedi() == null) {
                game.setSith(user);
            } else if (game.getSith() == null) {
                game.setJedi(user);
            }
            return gameService.updateGame(game);
        } else {
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ModelAndView flipSide(@ModelAttribute FlipGame game, ModelAndView view, HttpServletRequest request) {
        Boolean side = new Random().nextBoolean();
        Boolean result;
        if (side) {
            result = commitGameResult(side, game.getJedi(), game.getSith(), game.getBet());
        } else {
            result = commitGameResult(side, game.getSith(), game.getJedi(), game.getBet());
        }

        request.getSession(false).removeAttribute("currentGame");

        if (!result) {
            gameService.deleteGame(game.getGameId());
            view.addObject("error", "Something goes wrong, game is not accounted!");
            view.setViewName("main");
            return view;
        }

        game.setCompleted(false);
        gameService.updateGame(game);
        view.addObject("resultCoin",
                side ? "https://www.digitaltrends.com/wp-content/uploads/2011/08/star-wars-coins-2.png"
                        : "https://laughingsquid.com/wp-content/uploads/darth.png");
        view.addObject("result", resultService.createResult(new FlipResult(game, (side ? game.getJedi() : game.getSith()))));
        view.setViewName("game");
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
