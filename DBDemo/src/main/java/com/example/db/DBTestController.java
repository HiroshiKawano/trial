package com.example.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DBTestController {
    @Autowired ItemRepository repository;
    @RequestMapping(value="/")
    public String index(Model model) {
        List<Item> item_list = repository.findAll();
        model.addAttribute("item_list", item_list);
        return "index.html";
    }

  //追加メソッド（レコード登録フォームを表示）
    @RequestMapping(value="/crud")
    public String crud(Model model) {
        List<Item> item_list = repository.findAll();
        model.addAttribute("item_list", item_list);
    return "crud";
    }

    //追加メソッド（レコード登録後、全件取得）
    @RequestMapping(value="/insert")
    public String insert(
    @ModelAttribute(value="item") Item item,
    Model model) {
    repository.saveAndFlush(item);
    List<Item> item_list = repository.findAll();
    model.addAttribute("item_list", item_list);
    return "index";
    }

    //追加メソッド（レコードの削除を行う）
    @RequestMapping(value="/delete")
    public String delete(
    Integer id,
    Model model) {
    repository.deleteById(id);
    List<Item> item_list = repository.findAll();
    model.addAttribute("item_list", item_list);
    return "crud";
    }

    //追加メソッド（レコード変更のため、１件のみレコードを取得）
    @RequestMapping(value="/update_select")
    public String update_select(
    @RequestParam(value="id") Integer pid,
    Model model) {
    Optional<Item> item = repository.findById(pid); //Optional<nullになる可能性があるクラス名>
    if(item.isPresent()) model.addAttribute("item", item.get());//ラムダ式もＯＫ
    return "update";
    }

    //追加メソッド（レコード変更を行う、変更後全件取得）
    @RequestMapping(value="/update")
    public String update(
    @ModelAttribute(value="item") Item item,
    Model model) {
    repository.saveAndFlush(item);
    List<Item> item_list = repository.findAll();
    model.addAttribute("item_list", item_list);
    return "crud";
    }

    //追加メソッド
    @RequestMapping(value="/select")
    public String select(
    @ModelAttribute(value="item") Item item,
    Model model) {
    List<Item> item_list = repository.findByNameLikeOrderByIdAsc ("%ス%");
  //List<Item> item_list = repository.findByPriceGreaterThanAndPriceLessThanOrderByPriceAsc(5000, 20000);
  //List<Item> item_list = repository.myQuery("%ス%");
    model.addAttribute("item_list",item_list);
    return "index";
    }

    @Autowired PagerService page_service;
    @RequestMapping(value="item_list")
    public String pageList(
            @PageableDefault(page = 0, size= 2) Pageable pageable,
            Model model) {
        Page<Item> page = page_service.getAllPager(pageable);
        model.addAttribute("page", page);
        model.addAttribute("item_list", page.getContent());
        return "index";
    }

}
