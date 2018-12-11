package my.flipside.application.controllers;

import my.flipside.application.model.FlipGame;
import my.flipside.application.model.FlipUser;
import my.flipside.application.service.GameService;
import my.flipside.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/main")
@EnableTransactionManagement
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class MainController {

    @Autowired
    UserService userService;
    @Autowired
    GameService gameService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loadMainPage(HttpSession session, ModelAndView view, Principal principal,
                                     @RequestParam(required = false) String dismissGame,
                                     @RequestParam(required = false) String leaveGame) {

        session.setAttribute("user", userService.getUserByUsername(principal.getName()));

        Integer score = ((FlipUser) session.getAttribute("user")).getStat().getScore();
        if (score > 0) {
            session.setAttribute("mainBackground",
                    "https://cdn.hipwallpaper.com/i/91/42/k4fDga.jpg");
        } else if (score < 0) {
            session.setAttribute("mainBackground",
                    "https://wonderfulengineering.com/wp-content/uploads/2014/04/space-wallpapers-20.jpg");
        } else {
            session.setAttribute("mainBackground",
                    "https://d2v9y0dukr6mq2.cloudfront.net/video/thumbnail/moving-through-stars-in-space_-1zccenlb__F0000.png");
        }

        if (dismissGame != null) {
            gameService.deleteGame(Integer.parseInt(dismissGame));
            session.removeAttribute("creator");
            session.removeAttribute("currentGame");
        }
        if (leaveGame != null) {
            FlipGame game = gameService.getGame(Integer.parseInt(dismissGame));
            if (((FlipUser) session.getAttribute("user")).getUserId() == game.getSith().getUserId()) {
                game.setSith(null);
            } else {
                game.setJedi(null);
            }
            session.removeAttribute("currentGame");
        }

        view.addObject("availableGames",
                gameService.getGamesByCompleted(false)
                        .stream()
                        .filter(flipGame -> (flipGame.getJedi() == null || flipGame.getSith() == null))
                        .collect(Collectors.toList()))
                .setViewName("main");
        return view;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView createRoomByForm(HttpSession session, ModelAndView view, @RequestParam String bet, @RequestParam String side) {
        if (session.getAttribute("currentGame") != null) {
            view.addObject("error", "You are already in game!")
                    .setViewName("main");
            return view;
        }
        Integer newGameId = createGame(session, Integer.parseInt(bet), side);
        if (newGameId != null) {
            FlipGame newGame = gameService.getGame(newGameId);
            session.setAttribute("currentGame", newGame);
            session.setAttribute("creator", true);
            view.addObject("currentGame", newGame);
        } else {
            view.addObject("error", "Some error appears while creating your room. Try one more time");

        }
        view.setViewName("main");
        return view;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer createGame(HttpSession session, int bet, String side) {
        FlipGame game = new FlipGame();
        game.setCompleted(false);
        game.setBet(bet);
        FlipUser user = (FlipUser) session.getAttribute("user");
        if (user.getStat().getAccount() < game.getBet()) {
            return null;
        }
        if (side.equals("jedi")) {
            game.setJedi(user);
        } else if (side.equals("sith")) {
            game.setSith(user);
        } else {
            return null;
        }
        return gameService.createGame(game);
    }

}
