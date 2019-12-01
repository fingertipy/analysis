package analysis.server.impl;

import analysis.entity.SampleEntity;
import analysis.mapper.SampleMapper;
import analysis.server.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

/**
 * Smaple Service Implement
 */
@Service
public class SampleServiceImpl implements SampleService {

    //logger
    private static Logger logger = LoggerFactory.getLogger(SampleServiceImpl.class);

    @Autowired
    private SampleMapper sampleMapper;

    @Override
    public List<SampleEntity> selectAllSample() {
        try {
            return sampleMapper.selectAllSample();
        } catch (Exception e) {
            logger.error("select sample data exception:", e);
            return Collections.emptyList();
        }
    }
}
