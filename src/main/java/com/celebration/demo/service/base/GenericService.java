package com.celebration.demo.service.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: wjy
 * @Date: 2019/9/25 12:31
 * @Description: 基础服务
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
}
