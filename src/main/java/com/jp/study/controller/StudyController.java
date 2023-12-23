package com.jp.study.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jp.study.dto.DenpyoDto;
import com.jp.study.dto.SearchItemDto;
import com.jp.study.dto.ShohinDto;
import com.jp.study.entity.DenpyoEntity;
import com.jp.study.entity.ShohinEntity;
import com.jp.study.response.ResponseData;
import com.jp.study.service.CustomerService;
import com.jp.study.service.StudyService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class StudyController {
	
	private final StudyService studyService;
	
	private final CustomerService customerService; 
	
	@RequestMapping(value = "/" , method = {RequestMethod.GET})
	public String show(Model model) {
		
		List<DenpyoEntity> denpyoList = studyService.selectAllDenpyoList();
//		LocalDateTime nowDateTime = LocalDateTime.now();
//		String nowTimeString = nowDateTime.toString().substring(0, 19).replace("T", " ");
//	
//		model.addAttribute("now", nowTimeString);
		model.addAttribute("denpyoList", denpyoList);
		
		return "study/Study";
	}
	
	
	@RequestMapping(value = "/New", method = {RequestMethod.GET})
	public String newDenpyo(Model model) {
		
		List<ShohinEntity> shohinList = studyService.getShohinList();
		
		DenpyoDto denpyoDto = new DenpyoDto();
		
		model.addAttribute("shohinList", shohinList);
		model.addAttribute("denpyoDto", denpyoDto);
		
		return "study/New";
	}
	
	@RequestMapping(value = "/Save", method = {RequestMethod.POST})
	public String saveDenpyo(@ModelAttribute DenpyoDto denpyoDto) {
		
		int result = customerService.findByCustomer(denpyoDto.getCustomerNameSei(), denpyoDto.getCustomerNameMei());
		
		if(result == 0) {
			customerService.insertNewCustomer(denpyoDto);
		}
		
		studyService.saveDenpyo(denpyoDto);
		 
		return "redirect:/";
	}
	
	@RequestMapping(value = "/ItemNew", method = {RequestMethod.GET})
	public String newItem(Model model) {
		
		model.addAttribute("shohin", new ShohinDto());	
		
		return "study/ItemNew";
	}

	@RequestMapping(value = "/ItemSave", method = {RequestMethod.POST})
	public String saveItem(@ModelAttribute ShohinDto shohinDto, RedirectAttributes redirectAttributes){
		String message = null;
		
		if(shohinDto.getZeinukiGaku() == 0) {
			message = "税抜額を0円では登録できません";
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:/ItemNew";
		}
		String newItemCd = studyService.registryNewItem(shohinDto);
		if(StringUtils.isNotEmpty(newItemCd)) {
			message = "商品コード「" + newItemCd + "」で登録しました";
		} else {
			message = "登録中にエラーが発生しました";
		}
		
		redirectAttributes.addFlashAttribute("message", message);
		
		return "redirect:/ItemIndex";
	}

	@RequestMapping(value = "/ItemIndex", method = {RequestMethod.GET})
	public String itemIndex(Model model){

		List<ShohinEntity> shohinList = new ArrayList<>();
		List<ShohinEntity> shohinCdList = studyService.getShohinList();
//		List<ShohinEntity> shohinList = studyService.getShohinList();
		SearchItemDto searchItem = new SearchItemDto();
		model.addAttribute("shohinCdList", shohinCdList);
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("shohinList", shohinList);

		return "study/ItemIndex";
	}
	
	@RequestMapping(value = "/ItemIndex", method = {RequestMethod.POST})
	public String searchItem(SearchItemDto searchItemDto, Model model) {
		
		String message = null;
		
		List<ShohinEntity> shohinList = new ArrayList<>();
		SearchItemDto searchItem = new SearchItemDto();
		ResponseData<SearchItemDto> response = new ResponseData<SearchItemDto>();
		
		if(StringUtils.equals(searchItemDto.getSearchKbn(), "0")) {
			response = studyService.searchItemDataList(searchItemDto);
			shohinList = studyService.getSearchItemList(response.getData());
		} else if (StringUtils.equals(searchItemDto.getSearchKbn(), "1")) {
			shohinList = studyService.getShohinList();
		}

		int count = shohinList.size();
		
		List<ShohinEntity> shohinCdList = studyService.getShohinList();
		
		model.addAttribute("shohinCdList", shohinCdList);
		model.addAttribute("message", message);
		model.addAttribute("count", "検索結果 : " + count + "件");
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("shohinList", shohinList);
		
		return "study/ItemIndex";
	}

	@RequestMapping(value = "ItemIndex/{shohinCd}", method = {RequestMethod.GET})
	public String editItem(@PathVariable String shohinCd, Model model){

		ShohinEntity entity = studyService.getShohinInfo(shohinCd);
		model.addAttribute("entity", entity);

		return "study/ItemEdit";
	}
	
	@RequestMapping(value = "/delete", method = {RequestMethod.POST})
	public String itemDelete(@RequestParam String shohinCd, RedirectAttributes redirectAttributes){
		String message = null;
		int result = studyService.deleteItem(shohinCd);
		
		if(result == 1) {
			message = "商品コード「" + shohinCd + "」を削除しました";
		} else {
			message = "商品コード「" + shohinCd + "」はすでに削除済みです";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/ItemIndex";
	}
	
	@RequestMapping(value = "/update", method = {RequestMethod.POST})
	public String updateItem(ShohinEntity shohinEntity, RedirectAttributes redirectAttributes) {
		String message = null;
		String shohinCd = shohinEntity.getShohinCd();
		
		if(shohinEntity.getZeinukiGaku() == 0) {
			message = "税抜額を0円では登録できません";
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:/ItemIndex/" + shohinCd;
		} 
		int result = studyService.updateItem(shohinEntity);
		if(result == 1) {
			message = "商品コード「" + shohinCd +"」を更新しました";
		}
		
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/ItemIndex";
	}
}
