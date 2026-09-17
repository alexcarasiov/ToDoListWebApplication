package org.example.controller;

import org.example.entity.Record;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

//    @RequestMapping("/home") // http://localhost:8080/prefix/home
//    public ModelAndView getMainPage(@RequestParam(value = "filter", required = false) String filterMode) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("main-page");
//
//        RecordsContainerDto recordDto = recordService.findAllRecords(filterMode);
//        modelAndView.getModelMap().addAttribute("records", recordDto.getRecords());
//        modelAndView.getModelMap().addAttribute("numberOfDoneRecords", recordDto.getNumberOfDoneRecords());
//        modelAndView.getModelMap().addAttribute("numberOfActiveRecords", recordDto.getNumberOfActiveRecords());
//        return modelAndView;
//    }

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
        return "redirect:/home" + (filterMode != null && !filterMode.isBlank() ? "?filter=" + filterMode : "");
        //  redirect:/home?filter=active
    }

//    @RequestMapping(value = "/remove-record", method = RequestMethod.POST)
//    public String removeRecord(@RequestParam int id,
//                               @RequestParam(name="filter", required = false) String filterMode) {
//        recordService.removeRecord(id);
//        return "redirect:/home" + (filterMode != null && !filterMode.isBlank() ? "?filter=" + filterMode : "");
//    }

    @RequestMapping(value = "/remove-record", method = RequestMethod.POST)
    public String removeRecord(QueryParameters queryParameters) {
        recordService.removeRecord(queryParameters.getId());
        return "redirect:/home" + (queryParameters.getFilter() != null && !queryParameters.getFilter().isBlank() ? "?filter=" + queryParameters.getFilter() : "");
    }

    class QueryParameters {
        private int id;
        private String filter;
        public QueryParameters(int id,  String filter) {
            this.id = id;
            this.filter = filter;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setFilter(String filter) {
            this.filter = filter;
        }

        public int getId() {
            return id;
        }

        public String getFilter() {
            return filter;
        }
    }

}
