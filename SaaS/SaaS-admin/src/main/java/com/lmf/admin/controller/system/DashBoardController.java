package com.saas.admin.controller.system;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.http.client.utils.DateUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.entity.AjaxResult;

@RestController
@RequestMapping("/system/dashboard")
public class DashBoardController extends BaseController {

	/**
	 * 获取用户列表
	 */
	@PreAuthorize("@ss.hasPermi('system:dashboard:query')")
	@GetMapping("/index")
	public AjaxResult index() {
		Random random = new Random();
		Map<String, Object> data = new HashMap<>();
		data.put("addContactNum", random.nextInt(100000));
		data.put("addFriendsNum", random.nextInt(100000));
		data.put("addIntoRoomNum", random.nextInt(100000));
		data.put("corpMemberNum", random.nextInt(100000));
		data.put("lastAddContactNum", random.nextInt(100000));
		data.put("lastAddFriendsNum", random.nextInt(100000));
		data.put("lastAddIntoRoomNum", random.nextInt(100000));
		data.put("lastLossContactNum", random.nextInt(100000));
		data.put("lastMonthAddRoomMemberNum", random.nextInt(100000));
		data.put("lastMonthAddRoomNum", random.nextInt(100000));
		data.put("lastMonthLossContactNum", random.nextInt(100000));
		data.put("lastQuitRoomNum", random.nextInt(100000));
		data.put("lossContactNum", random.nextInt(100000));
		data.put("monthAddRoomMemberNum", random.nextInt(100000));
		data.put("monthAddRoomNum", random.nextInt(100000));
		data.put("monthLossContactNum", random.nextInt(100000));
		data.put("quitRoomNum", random.nextInt(100000));
		data.put("roomMemberNum", random.nextInt(100000));
		data.put("weChatContactNum", random.nextInt(100000));
		data.put("weChatRoomNum", random.nextInt(100000));
		data.put("updateTime", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return AjaxResult.success(data);
	}

	@PreAuthorize("@ss.hasPermi('system:dashboard:query')")
	@GetMapping("/lineChat")
	public AjaxResult lineChat() {
		Map<String, Object> data = new HashMap<>();
		Integer[] addContactNum = new Integer[] { 101, 929, 829, 101, 201 };
		Integer[] addIntoRoomNum = new Integer[] { 90, 99, 89, 100, 120 };
		Integer[] lossContactNum = new Integer[] { 10, 20, 19, 10, 20 };
		Integer[] quitRoomNum = new Integer[] { 1, 9, 9, 2, 15 };
		String[] time = new String[] { "1月5日", "1月6日", "1月7日", "1月8日", "1月9日" };
		JSONArray array = new JSONArray();
		for (int i = 0; i < time.length; i++) {
			JSONObject ne = new JSONObject();
			ne.put("addContactNum", addContactNum[i]);
			ne.put("addIntoRoomNum", addIntoRoomNum[i]);
			ne.put("lossContactNum", lossContactNum[i]);
			ne.put("quitRoomNum", quitRoomNum[i]);
			ne.put("date", time[i]);
			array.add(ne);
		}
		return AjaxResult.success(array);
	}

	@PreAuthorize("@ss.hasPermi('system:dashboard:query')")
	@GetMapping("/tenantIndex")
	public AjaxResult tenantIndex() {
		Map<String, Object> data = new HashMap<>();
		JSONObject license = new JSONObject();
		license.put("licenseType", "旗舰版本");
		license.put("licenseNote", "增强功能版本，包括各种扩展功能");
		data.put("license", license);
		data.put("licenseContactLink", "https://cs.tenfenbook.com/B2BH5/");
		data.put("guide", "guide");
		JSONArray news = new JSONArray();
		JSONObject ne = new JSONObject();
		ne.put("createdAt", "2020年12月1日");
		ne.put("title", "旗舰版本1.0.0");
		news.add(ne);
		data.put("news", news);
		return AjaxResult.success(data);
	}
}
