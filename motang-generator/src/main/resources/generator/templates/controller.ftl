package ${basePackage}.${controllerPackage};

import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${servicePackage}.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
*  @Description ${tableComment!} Controller
*  @author ${author}
*  @Date ${.now}
*/
@RestController
@RequestMapping("${className?uncap_first}")
public class ${className}Controller {

    private  ${className}Service ${className?uncap_first}Service;

}
