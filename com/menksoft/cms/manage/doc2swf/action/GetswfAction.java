package com.menksoft.cms.manage.doc2swf.action;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menksoft.cms.manage.doc2swf.Doc2swf;
import com.menksoft.util.Const;

/**
 * word转换swf
 * @author 呼和
 */
@Controller
public class GetswfAction {
	@RequestMapping(value="/doc/getswf/{file}", method=RequestMethod.GET)
	public  @ResponseBody  String getSwf(@PathVariable("file") String file){
		 ExecutorService pool = Executors.newFixedThreadPool(1);
		 file=Const.APP_ROOT+"website\\www\\contentDoc\\"+file;
		 Doc2swf ds=new Doc2swf(file);
		 Future f1 = pool.submit(ds);
		 String outfile="";
		 try {
			outfile= f1.get().toString();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	        // 关闭线程池
	        pool.shutdown();
		
		return outfile;
	}
}
