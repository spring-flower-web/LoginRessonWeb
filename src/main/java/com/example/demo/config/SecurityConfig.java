package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//設定クラスを表すアノテーション
@Configuration
//Spring Securityを有効化するアノテーション(絶対必須)
@EnableWebSecurity

public class SecurityConfig {
	
	//newでインスタンス化しないでも使えるspring特有のアノテーション
	//Beanは戻り値をSpringが管理するので、他のクラスから自動で使えるようになる
	@Bean
	//パスワードをハッシュ化(暗号化)するためのインターフェース
	PasswordEncoder passwordEncoder(){
		//BCyptという安全性の高いハッシュアルゴリズム
		//Spring Security ではほぼ標準
		return new BCryptPasswordEncoder();
		//まとめ：ユーザー登録時やログイン時に、パスワードを安全に扱うための設定
	}
	
	@Bean
	//SecurityFilterChain→リクエストが来た時に、どんなセキュリティチェックを通すかを定義するもの
	//Spring Security は「フィルターの連鎖」で動く
	//HttpSecurity http→HTTP通信(URLアクセス)に対するセキュリティ設定を行うためのオブジェクト
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			//「どのURLに誰がアクセスできるか」**を決める部分
			.authorizeHttpRequests(auth -> auth
				//login と /register というURLを指定
				//誰でもアクセスOK
				.requestMatchers("/login","register").permitAll()
				//上で指定していない それ以外のすべてのURL
				//ログイン（認証）済みでないとアクセス不可
				.anyRequest().authenticated()
			)
			//フォームログインを使うという設定
			.formLogin(login -> login
					//ログイン画面のURLを /login にする
					//自作のログインHTMLを使うときに必須;
					.loginPage("/login")
					//ログイン成功後に遷移するURL
					//tasks に必ず飛ばす
					//true→どこから来たかに関係なく、常に /tasks に行く
					.defaultSuccessUrl("/tasks",true)
			)
			//ログアウト処理の設定
			.logout(logout -> logout
					//ログアウト成功後に /login に戻す
					.logoutSuccessUrl("/login")
			);
		//ここまで書いた設定を Spring Security に適用
		//「設定完了！」というイメージ
		return http.build();
	}
}
