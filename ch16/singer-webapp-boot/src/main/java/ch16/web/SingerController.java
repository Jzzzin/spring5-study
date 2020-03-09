package ch16.web;

import ch16.entities.Singer;
import ch16.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/singers")
public class SingerController {

    private final Logger logger = LoggerFactory.getLogger(SingerController.class);

    @Autowired
    SingerService singerService;

    @GetMapping
    public String list(Model uiModel) {
        logger.info("Listing singers");
        List<Singer> singers = singerService.findAll();
        uiModel.addAttribute("singers", singers);
        logger.info("No. of singers: " + singers.size());
        return "singers";
    }

    @GetMapping(value = "/{id}")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        Singer singer = singerService.findById(id);
        uiModel.addAttribute("singer", singer);
        return "show";
    }

    @GetMapping(value = "/edit/{id}")
    public String updateForm(@PathVariable Long id, Model uiModel) {
        uiModel.addAttribute("singer", singerService.findById(id));
        return "update";
    }

    @GetMapping(value = "/new")
    public String createForm(Model uiModel) {
        Singer singer = new Singer();
        uiModel.addAttribute("singer", singer);
        return "update";
    }

    @PostMapping
    public String saveSinger(@Valid Singer singer) {
        singerService.save(singer);
        return "redirect:/singers/" + singer.getId();
    }
}
