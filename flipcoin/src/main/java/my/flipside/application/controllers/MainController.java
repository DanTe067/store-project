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

import javax.servlet.http.HttpServletRequest;
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
    public ModelAndView loadMainPage(HttpServletRequest request, ModelAndView view, Principal principal, @RequestParam(required = false) String dismissGame) {
        if (request.getSession(false).getAttribute("user") == null) {
            request.getSession(false).setAttribute("user", userService.getUserByUsername(principal.getName()));
        }

        if (request.getSession(false).getAttribute("mainBackground") == null) {
            Integer score = ((FlipUser) request.getSession(false).getAttribute("user")).getStat().getScore();
            if (score > 0) {
                request.getSession(false).setAttribute("mainBackground",
                        "https://cdn.hipwallpaper.com/i/91/42/k4fDga.jpg");
            } else if (score < 0) {
                request.getSession(false).setAttribute("mainBackground",
                        "https://wonderfulengineering.com/wp-content/uploads/2014/04/space-wallpapers-20.jpg");
            } else {
                request.getSession(false).setAttribute("mainBackground",
                        "https://d2v9y0dukr6mq2.cloudfront.net/video/thumbnail/moving-through-stars-in-space_-1zccenlb__F0000.png");
            }
        }

        if (dismissGame != null) {
            gameService.deleteGame(Integer.parseInt(dismissGame));
            request.getSession(false).removeAttribute("currentGame");
        }

        view.addObject("availableGames",
                gameService.getGamesByCompleted(false)
                        .stream()
                        .filter(flipGame -> (flipGame.getJedi() == null || flipGame.getSith() == null))
                        .collect(Collectors.toList()));
        view.setViewName("main");
        return view;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView createRoomByForm(HttpServletRequest request, ModelAndView view) {
        if (request.getSession(false).getAttribute("currentGame") != null) {
            view.addObject("error", "You are already in game!");
            view.setViewName("main");
            return view;
        }
        Integer newGameId = createGame(request);
        if (newGameId != null) {
            request.getSession(false).setAttribute("currentGame", gameService.getGame(newGameId));
            view.addObject("currentGame", gameService.getGame(newGameId));
            view.setViewName("game");
        } else {
            view.addObject("error", "Some error appears while creating your room. Try one more time");
            view.setViewName("main");
        }
        return view;
    }

//    @RequestMapping(value = "/main/enter", method = RequestMethod.PUT)
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public ModelAndView enterRoom(HttpServletRequest request, ModelAndView view, @RequestParam Integer gameId) {
//        if (request.getSession(false).getAttribute("currentGame") != null) {
//            view.addObject("error", "You are already in game!");
//            view.setViewName("main");
//        } else {
//            if (enterGameById(gameId, (FlipUser) request.getSession(false).getAttribute("user")) != null) {
//                request.getSession(false).setAttribute("currentGame", gameService.getGame(gameId));
//                view.setViewName("game");
//            } else {
//                view.addObject("error", "Could not join room!");
//                view.setViewName("main");
//            }
//        }
//        return view;
//    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer createGame(HttpServletRequest request) {
        FlipGame game = new FlipGame();
        game.setCompleted(false);
        game.setBet(Integer.parseInt(request.getParameter("bet")));
        FlipUser user = (FlipUser) request.getSession(false).getAttribute("user");
        if (user.getStat().getAccount() < game.getBet()) {
            return null;
        }
        if (request.getParameter("side").equals("jedi")) {
            game.setJedi(user);
        } else if (request.getParameter("side").equals("sith")) {
            game.setSith(user);
        } else {
            return null;
        }
        return gameService.createGame(game);
    }

}
