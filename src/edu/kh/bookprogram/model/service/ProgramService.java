package edu.kh.bookprogram.model.service;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.bookprogram.model.vo.Favorites;
import edu.kh.bookprogram.model.vo.Program;

public class ProgramService {

	private Scanner sc = new Scanner(System.in);//스캐너
	
	private List<Program> BookList = new ArrayList<Program>();//ArrayList에 Program을 제네릭스 한것, 도서 목록
	private List<Favorites> FavoritesList = new ArrayList<Favorites>();// 즐겨찾기 목록
	
	public ProgramService() { //ProgramList의 기본 생성자
		
		BookList.add(new Program("용의자 X의 헌신", "히가시노게이고", 15120, "제인출판사"));//도서 목록 기본 생성자
		FavoritesList.add(new Favorites("용의자 X의 헌신", "히가시노 게이고"));//즐겨찾기 목록 기본생성자
	}
	
	public void displayMenu() {//프로그램 시작시 보여주는 화면, do while문으로 0번을 누를때까지 반복하는 프로그램
		int menuNum = 0;
		
		do { //프로그램 목록
			System.out.println("\n=======도서 목록 프로그램=======\n");
			System.out.println("1.도서 추가 등록");
			System.out.println("2.도서 전체 조회");
			System.out.println("3.도서 정보 수정");
			System.out.println("4.도서 정보 삭제");
			System.out.println("5.도서 즐겨찾기 추가");
			System.out.println("6.즐겨찾기 목록 조회");
			System.out.println("7.도서 즐겨찾기 취소");
			
			System.out.println("0.프로그램 종료");
			
			System.out.println("메뉴 선택 >>");
			
			try {//메뉴를 입력했을경우 case에 맞게 들어가서 프로그램을 수행
				menuNum = sc.nextInt();
				System.out.println(); // 줄바꿈
				
				
				switch(menuNum) {
				case 1 : System.out.println(addBook()); break;//도서 추가 들록
				case 2 : selectAll(); break;//도서 존체 조회
				case 3 : System.out.println(updateBook()); break;//도서 정보 수정
				case 4 : System.out.println(removeBook()); break;//도서 정보 삭제
				case 5 : System.out.println(favoritesBook()); break;//즐겨찾기 추가
				case 6 : output();break;//즐겨찾기 조회
				case 0 : System.out.println("프로그램 종료"); break;//프로그램 종료
				default : System.out.println("올바른 번호를 입력하시오");//다른 번호를 입력시
				
				}
				
			}catch(InputMismatchException e) {//잘못된 입력에 대한 catch문
				
				System.out.println("잘못된 입력");
				sc.nextLine();//문자 제거용
				menuNum = -1; // 잘못된 입력시 프로그램 종료가 아니라 do문으로 돌아가기 위한 -1
			}
			
			
			
		}while(menuNum !=0);
		
		
	}
	
	public String addBook() throws InputMismatchException {//도서 정보 추가
		
		System.out.println("=====도서 정보 추가======");
		
		System.out.println("제목");
		String title = sc.next();
		
		System.out.println("저자");
		String author = sc.next();
		sc.nextLine();//개행문 필수 없을시 프로그램 강제종료
		
		System.out.println("가격");
		int price = sc.nextInt();
		
		System.out.println("출판사");
		String publisher = sc.next();
		
		//book 객체 추가
		if(BookList.add(new Program(title, author, price, publisher)) ){
			return "추가 완료";
		}else {
			return "추가 실패";
		}
	}
	
	public void selectAll() {//도서 전체 조회
		
		System.out.println("=====도서 목록=====");
		
		//isEmpty()문을 사용하여 먼저 정보가 비어있는지 확인
		
		if(BookList.isEmpty()) {
			System.out.println("도서 정보가 없음");
			
			return;// 비어있지 않을경우 다시 돌아가서 수행
		}
		
		//향상된 for문으로 각 인덱스에 있는 정보에 접근후 출력
		int index = 0;
		
		for(Program pro : BookList ) {
			System.out.print((index++) + "번 : ");
			
			System.out.println(pro);
		}
		
	}
	
	public String updateBook() throws InputMismatchException {//도서 정보 수정
		
		System.out.println("=====도서 정보 수정=====");
		
		System.out.println("수정할 도서 번호 입력");
		int index = sc.nextInt();
		sc.nextLine();//개행
		
		//먼저 입력에 오류가 있나 검사
		//1. 도서 정보가 비어 있는지 확인
		if(BookList.isEmpty()) {
			return "도서 정보가 없음";
			
		//2. 입력한 인덱스가 음수인지 확인	
		}else if(index < 0) {
			return"음수는 입력불가";
			
		//3. 입력한 인덱스가 범위내에 목록에 존재하는지 확인 size문 사용
		}else if(index >= BookList.size()) {
			return"목록을 벗어난 입력";
		
		
		//위 에서 오류가 없다면 이제 수정 시작
		}else {
			
			//기존 도서 정보
			System.out.println(index + "번쨰 도서 정보");
			System.out.println(BookList.get(index));
			
			//수정할 정보 입력
			
			System.out.println("제목");
			String title = sc.next();
			
			System.out.println("저자");
			String author = sc.next();
			sc.nextLine();
			
			System.out.println("가격");
			int price = sc.nextInt();
			sc.nextLine();
			
			System.out.println("출판사");
			String publisher = sc.next();
			
			Program temp = BookList.set(index, new Program(title, author, price, publisher));
			
			return temp.getName() + "도서 정보 수정 완료";
			
		}
		
		
	}
	
	public String removeBook() throws InputMismatchException {//도서 정보 제거
		
		System.out.println("=====도서 정보 제거=====");
		
		System.out.print("제거할 도서 번호 입력");
		int index =sc.nextInt();
		sc.nextLine();//개행
		
		//먼저 입력에 오류가 있나 검사
		//1. 도서 정보가 비어 있는지 확인
		if(BookList.isEmpty()) {
					return "도서 정보가 없음";
					
		//2. 입력한 인덱스가 음수인지 확인	
		}else if(index < 0) {
					return"음수는 입력불가";
					
		//3. 입력한 인덱스가 범위내에 목록에 존재하는지 확인 size문 사용
		}else if(index >= BookList.size()) {
					return"목록을 벗어난 입력";
				
				
				//위 에서 오류가 없다면 이제 수정 시작
		}else {
			
			System.out.println("정말 삭제하시겠습니까? (Y/N) : ");//삭제 최종 컨펌문구
			//UpperCase문구를 사용하여 무조건 대문자로 치환
			char ch = sc.next().toUpperCase().charAt(0);
				
			if(ch == 'Y') {
				Program temp = BookList.remove(index);
				return temp.getName() + "의 도서 정보 제거 완료";
				
			}else {
				return "정보 제거 취소";
			
			}
		}
		
		
	}
	
	public String favoritesBook() throws InputMismatchException {
		
		System.out.println("=====즐겨찾기 추가=====");
		
		System.out.println("제목");
		String title = sc.next();
		
		
		System.out.println("저자");
		String author = sc.next();
		sc.nextLine();
		
		if(FavoritesList.add(new Favorites(title, author))){
			return "추가 완료";
			
		}else {
			return "추가 실패";
		}
		
		
		

	}
	
	public void output() {//도서 즐겨찾기 조회
		
		
		FileWriter fw = null;
		System.out.println("=====즐겨찾기 추가=====");
		try {
			
			fw = new FileWriter("favorites.txt",true);

				String str = "112242354";
			
			
				fw.write(str);
			
			
			
		}catch(IOException e) {
			
			System.out.println("예외발생");
			e.printStackTrace();
			
		}finally {//예외가 발생해도 무조건 수행하는 구문
			
		}try {
			fw.close();
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		

		
	}
}
