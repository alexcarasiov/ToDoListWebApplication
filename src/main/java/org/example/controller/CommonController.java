package org.example.controller;

import org.example.entity.RecordPriority;
import org.example.entity.RecordStatus;
import org.example.entity.dto.RecordsContainerDto;
import org.example.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/prefix")
public class CommonController {

    private final RecordService recordService;

    @Autowired
    public CommonController(RecordService recordService) {
        this.recordService = recordService;
    }

    @RequestMapping("/")
    public String redirectToHomePage() {
        return "redirect:/home";
    }

    @RequestMapping("/home") // http://localhost:8080/prefix/home
    public String getMainPage(Model model, @RequestParam(value = "filter", required = false) String filterMode) {
        RecordsContainerDto recordDto = recordService.findAllRecords(filterMode);
        model.addAttribute("records", recordDto.getRecords());
        model.addAttribute("numberOfDoneRecords", recordDto.getNumberOfDoneRecords());
        model.addAttribute("numberOfActiveRecords", recordDto.getNumberOfActiveRecords());
        return "main-page";
    }

    @RequestMapping(value = "/add-record", method = RequestMethod.POST) // http://localhost:8080/prefix/home
    public String addRecord(@RequestParam String title, @RequestParam(required = false) RecordPriority priority) {
        if (title != null && !title.isBlank()) {
            recordService.saveRecord(title, priority != null ? priority : RecordPriority.MEDIUM);
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/make-record-done", method = RequestMethod.POST)
    public String makeRecordDone(@RequestParam int id,
                                 @RequestParam(name="filter", required = false) String filterMode) {
        recordService.updateRecordStatus(id, RecordStatus.DONE);
        return buildHomeRedirect(filterMode);
    }

    @RequestMapping(value = "/remove-record", method = RequestMethod.POST)
    public String removeRecord(@RequestParam int id,
                               @RequestParam(name="filter", required = false) String filterMode) {
        recordService.removeRecord(id);
        return buildHomeRedirect(filterMode);
    }

    private String buildHomeRedirect(String filterMode) {
        return "redirect:/home" + (filterMode != null && !filterMode.isBlank() ? "?filter=" + filterMode : "");
    }

}
