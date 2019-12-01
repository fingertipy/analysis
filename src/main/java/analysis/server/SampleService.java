package analysis.server;

import analysis.entity.SampleEntity;

import java.util.List;

/**
 * Sample Service
 */
public interface SampleService {

    /**
     * select all sample
     * @return
     */
    List<SampleEntity> selectAllSample();
}
