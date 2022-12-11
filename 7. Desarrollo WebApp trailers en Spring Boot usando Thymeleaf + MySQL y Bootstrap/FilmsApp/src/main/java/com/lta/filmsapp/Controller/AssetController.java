package com.lta.filmsapp.Controller;

import com.lta.filmsapp.Service.Interface.IToolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assets")
public class AssetController {
    @Autowired
    private IToolsService its;

    @GetMapping("/{filename:.+}")
    public Resource GetResource(@PathVariable("filename")String filename){
        return its.LoadAsResource(filename);
    }
}
