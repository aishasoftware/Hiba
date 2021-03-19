package aisha.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import aisha.bean.BasicBean;
import aisha.bean.PlatformUser;
import aisha.dao.BasicDAO;
import aisha.util.bean.FieldAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class utilMethods {

	@Autowired
    private  BasicDAO basicDAO;
	
	
	public List<BasicBean> getOptions(BasicBean basic)
	{  
	BasicBean result = basicDAO.listBeans(basic);
	List<BasicBean> results = result.getResults();
	return results;
	}
	
}
