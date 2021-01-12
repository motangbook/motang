package ${basePackage}.${serviceImplPackage};

import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${mapperPackage}.${className}Mapper;
import ${basePackage}.${servicePackage}.I${className}Service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

/**
*  @Description ${tableComment!} 业务层实现类
*  @author ${author}
*  @Date ${.now}
*/
@Slf4j
@Service
public class ${className}ServiceImpl  implements I${className}Service {

    @Autowired
    private  ${className}Mapper ${className?uncap_first}Mapper;

}
