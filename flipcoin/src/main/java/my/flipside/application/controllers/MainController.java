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

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public ModelAndView loadMainPage(HttpServletRequest request, ModelAndView view, Principal principal) {
        if (request.getSession(false).getAttribute("user") == null) {
            request.getSession(false).setAttribute("user", userService.getUserByUsername(principal.getName()));
        }
        view.addObject("currentGames",
                gameService.getGamesByCompleted(false)
                        .stream()
                        .filter(flipGame -> (flipGame.getJedi() == null || flipGame.getSith() == null))
                        .collect(Collectors.toList()));
        view.setViewName("main");
        return view;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView createRoomByForm(HttpServletRequest request, HttpServletResponse response, ModelAndView view) {
        Integer newGameId = createGame(request);
        if (newGameId != null) {
            response.addCookie(new Cookie("currentGame", newGameId.toString()));
            view.setViewName("game");
        } else {
            view.addObject("error", "Some error appear while creating your room. Try one more time");
            view.setViewName("main");
        }
        return view;
    }

    @RequestMapping(value = "/main/enter", method = RequestMethod.PUT)
    public ModelAndView enterRoom(HttpServletRequest request, HttpServletResponse response, ModelAndView view, @RequestParam Integer gameId) {
        if (isInGame(request.getCookies())) {
            view.addObject("error", "You are already in game!");
        } else {
            if (enterGameById(gameId, (FlipUser) request.getSession(false).getAttribute("user")) != null) {
                response.addCookie(new Cookie("currentGame", gameId.toString()));
                try {
                    request.getRequestDispatcher("/game?gameId=" + gameId).forward(request, response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        view.setViewName("main");
        return view;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public FlipGame enterGameById(Integer gameId, FlipUser user) {
        FlipGame game = gameService.getGame(gameId);
        if (game != null) {
            if (game.getJedi() != null) {
                game.setSith(user);
            } else {
                game.setJedi(user);
            }
            return gameService.updateGame(game);
        } else {
            return null;
        }
    }

    private Boolean isInGame(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("currentGame")) {
                return true;
            }
        }
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer createGame(HttpServletRequest request) {
        FlipGame game = new FlipGame();
        game.setCompleted(false);
        game.setBet(Integer.parseInt(request.getParameter("bet")));
        FlipUser user = (FlipUser) request.getSession(false).getAttribute("user");
        if (request.getParameter("side").equals("jedi")) {
            game.setJedi(user);
        } else {
            game.setSith(user);
        }
        return gameService.createGame(game);
    }

}
