package postoffice.controller;

import java.util.Scanner;

import postoffice.model.MemberService;
import postoffice.model.PostService;
import postoffice.view.MemberView;
import postoffice.view.PostView;
import postoffice.vo.MemberVO;
import postoffice.vo.PostVO;

public class MemberController {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		MemberService memberService = new MemberService();
		PostService postService = new PostService();
		while(true) {
			System.out.println("1.회원가입");
			System.out.println("2.로그인");


			System.out.println("3.종료");
			String n=sc.next();
			switch(n) {
			case"1":{
				System.out.println("회원가입을 진행하겠습니다.");
				MemberVO mem=makeEmp2(sc);
				
				MemberView.print(memberService.memInsert(mem));
				System.out.println("회원가입을 축하합니다!");
				break;
			}
			
			case"2":{
				System.out.println("아이디: ");
				String memId = sc.next();
				System.out.println("비밀번호: ");
				String pass = sc.next();
				MemberView.print(memberService.memLogin(memId, pass));
			
				//System.out.println("4.우편물 접수");	
				//System.out.println("5.마이페이지");
				
				break;
				}
				
			case "3":{
				//break;
				System.out.println("종료되었습니다.");
				return;
			}

			
			}
			}
		}



	private static MemberVO makeEmp2(Scanner sc) {
		System.out.print("1.이름을 입력하세요>>");
		String name = sc.next();
		System.out.print("2.원하는 아이디를 입력하세요>>");
		String id = sc.next();
		System.out.print("3.전화번호를 입력하세요>>");
		String contact = sc.next();
		System.out.print("4.주소를 입력하세요>>");
		String address = sc.next();
		System.out.print("5.이메일을 입력하세요>>");
		String email = sc.next();
		System.out.print("6.비밀번호를 입력하세요>>");
		String pass = sc.next();
		
		MemberVO mem = new MemberVO();
		mem.setName(name);
		mem.setContact(contact);
		mem.setAddress(address);
		mem.setEmail(email);
		mem.setPassword(pass);
		mem.setMemberid(id);
		
		
		return mem;
	}
	}

