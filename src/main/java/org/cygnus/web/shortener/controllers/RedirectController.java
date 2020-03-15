package org.cygnus.web.shortener.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tiny")
public class RedirectController {

//    @GetMapping("/{shortenStr}")
//    public ModelAndView redirectToOriginalUrl(@PathVariable("shortenStr") String shortenStr) {
//        return urlService.getShortenUrl(shortenStr)
//                .map(shortenUrl -> new ModelAndView("redirect:" + shortenUrl.getOriginalUrl()))
//                .orElseGet(() -> new ModelAndView("tiny_not_found"));
//    }
}
