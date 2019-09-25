package com.celebration.demo.service.base;

import com.alibaba.fastjson.JSON;
import com.celebration.demo.common.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.ClientProtocolException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wjy
 * @date: 2019/8/12
 * @description: 基础服务
 */
@Slf4j
@Service
public class GenericService {

	public Map pagination(Page page) {

		Map<String, Object> map = new HashMap();
		// 分页当前页数
		map.put("pageNumber", page.getNumber());
		// 分页大小
		map.put("pageSize", page.getSize());
		// 查询结果总数
		map.put("totalCount", page.getTotalElements());
		// 分页总页数
		map.put("totalPage", page.getTotalPages());
		// 是否有下一页
		if ((page.getNumber() + 1) < page.getTotalPages()) {
			map.put("hasNext", 1);
		}
		else {
			map.put("hasNext", 0);
		}
		return map;
	}

	public void saveCode(String url, Map param, Long time) {

		try {
			InputStream inputStream = HttpClientUtil.doPost2(url, JSON.toJSONString(param));
			File targetFile = new File("/usr/local/javaweb/xdxlb/images");
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			FileOutputStream out = new FileOutputStream("/usr/local/javaweb/xdxlb/images/" + time + ".jpeg");
			log.info("生成二维码: {}", "/usr/local/javaweb/xdxlb/images/" + time + ".jpeg");
			byte[] buffer = new byte[8192];
			int bytesRead = 0;
			while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e) {
			log.error("异常: {}，异常信息: {}", e, e.getMessage());
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			log.error("异常: {}，异常信息: {}", e, e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.error("异常: {}，异常信息: {}", e, e.getMessage());
			e.printStackTrace();
		}
	}
}
