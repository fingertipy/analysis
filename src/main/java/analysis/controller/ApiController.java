package analysis.controller;

import analysis.entity.SampleEntity;
import analysis.server.SampleService;
import analysis.utils.PythonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * API Controller
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private SampleService sampleService;

    @GetMapping("/sample")
    public List<SampleEntity> sample(){
        return sampleService.selectAllSample();
    }

    @GetMapping("/exec")
    public void exec(){
        String pypath = "D:\\repository\\analysis_python\\analysis\\model.py";
        PythonUtils.exec(pypath);
    }
}
