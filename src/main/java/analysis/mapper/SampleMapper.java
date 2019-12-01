package analysis.mapper;

import analysis.entity.SampleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Sample Mapper
 */
@Mapper
public interface SampleMapper {
    /**
     * select all Sample data
     * @return
     */
     List<SampleEntity> selectAllSample();
}
