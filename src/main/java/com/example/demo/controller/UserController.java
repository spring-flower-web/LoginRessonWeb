package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.UserService;

@Controller
public class UserController {
	
	//スプリングが自動でユーザーが作成したクラスをインスタンス化してくれる
	//new 〇〇の部分を省略できる
	@Autowired
	private UserService userService;
	
	@RequestMapping("/register")
	public String registerForm() {
		return "register";
	}
	
	//WebサーバーにPost(データを送るよ)のときに呼び出される
	//逆の@GetMappigはWebサーバーからデータを取得するときに使用
	//ォーム送信・登録処理・ログイン処理でよく使う！
	@PostMapping("/register")
	public String register(
			//リクエストパラメータ
			//Webサイトとかで受け取った値をそのまま変数に格納する
			//フロントで入力された値を受け取って、処理で使える
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam String name) {
		
		userService.register(username,password,name);
		return "redirect:/login";
	}
	

}
