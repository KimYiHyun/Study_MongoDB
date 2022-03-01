import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.MongoClient;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class StudentManage {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int select;
		
		boolean run = true;
		
		String dbName;  //db이름
		String colName; //컬렉션 이름
		MongoCollection<Document> mycol = null;
		
		
		int stnum = 0;  //학번
		String stname = null;  //이름
		String belong = null;  //소속
		String college = null; //단과대학
		String depart = null;  //학과명
		int grade = 0;      //학년
		String gender = null;  //성별
		String email = null;   //이메일
		String hobby = null; //취미
		
		
		//서버 연결
		MongoClient myclient = new MongoClient("localhost", 27017);   //mongo 클라이언트 생성
		System.out.println("Connected to the database successfully");  //나중에 생략하기
		
		
		while(run) {
			
			System.out.println("<<학생 정보 관리 프로그램>>");
			System.out.println("1) 데이터베이스 및 컬렉션 입력/선택");
			System.out.println("2) 학생 정보 삽입");
			System.out.println("3) 학생 정보 삭제");
			System.out.println("4) 학생 정보 수정");
			System.out.println("5) 학생 정보 검색 (전체)");
			System.out.println("6) 학생 정보 검색 (조건)");
			System.out.println("7) 종료");
			System.out.println("----------------------");
			System.out.println("메뉴 선택 : " );
			select = sc.nextInt();
			
			if (select == 1) {
				//db 입력받기
				System.out.println("데이터베이스 이름을 입력하시오 : ");
				dbName = sc.next();
				MongoDatabase mydb = myclient.getDatabase(dbName); //db에 접근 (db가 없으면 처음 문서 저장 시 새로 생성)
				
				//컬랙션 입력받기
				System.out.println("컬렉션 이름을 입력하시오 : ");
				colName = sc.next();
				mydb. createCollection(colName); 
				System.out.println("Collection created succesfully");
				
				//컬렉션 선택(반환)
				mycol = mydb.getCollection(colName); //컬렉션 없으면 처음 문서 저장 시 새로 생성
				System.out.println("collection selected successfully");
				
				//db, 컬렉션 리스트 출력
				for (String name : ((MongoClient) mydb).listDatabaseNames()) {   //MongoClient추가 -----> 나중에 제거해야함
					System.out.println(name);
				}
				
				for (String name : mydb.listCollectionNames()) {
					System.out.println(name);
				}
			}
			
			//2) 학생 정보 삽입
			else if  (select == 2) {

				System.out.println("문서를 삽입하시오 ");
				
				List<Document> documents = new ArrayList<Document>();
				for (int i = 0; i< 100; i++) {
					
					System.out.println("학번:");
					stnum = sc.nextInt();
					System.out.println("이름:");
					stname = sc.next();
					System.out.println("단과대학:");
					college = sc.next();
					System.out.println("학과명:");
					depart = sc.next();
					System.out.println("학년:");
					grade = sc.nextInt();
					System.out.println("성별:");
					gender = sc.next();
					System.out.println("취미:");
					hobby = sc.next();
					
					
					documents.add(new Document("stnum", stnum)
							.append("stname", stname)
							.append("belong", new Document("college", college).append("depart", depart))
							.append("grade", grade)
							.append("gender", gender)
							.append("hobby", Arrays.asList(hobby))
							);
				}
				mycol.insertMany(documents);
				System.out.println("Document inserted successfully");
				
				
			}
			
			//3) 학생 정보 삭제
			else if (select == 3) {
				
				System.out.println("삭제할 학생의 학번을 입력하시오. ");
				stnum = sc.nextInt();
				
					
				mycol.deleteOne(Filters.eq("stnum", stnum));
				System.out.println("Document deleted successfully...");
					
				// Getting the iterable object 
				FindIterable<Document> iterDoc = mycol.find(); 
				int i = 1; 
				// Getting the iterator 
				Iterator it = iterDoc.iterator(); // iterator 반환
				while (it.hasNext()) { 
				 System.out.println(it.next()); i++;
				}
								

			}
			
			//4) 학생 정보 수정
			else if (select == 4) {
				
				mycol.updateOne(Filters.eq("stnum", stnum), Updates.set("likes", 150)); 
				 System.out.println("Document update successfully..."); 
				 
				 
				 // Getting the iterable object 
				 FindIterable<Document> iterDoc = mycol.find(); 
				 // Getting the iterator 
				 Iterator it = iterDoc.iterator(); // iterator 반환
				 int i = 1; 
				 while (it.hasNext()) { 
				 System.out.println(it.next()); i++;
				 }

			}
			
			//5) 학생 정보 검색 (전체)
			else if (select == 5) {
				
				
			}
			
			//6)학생 정보 검색 (조건)
			else if (select == 6) {
				
				
			}
			
			else {
				run = false;
				System.out.println("프로그램 종료!");
			}
			
		}
		
		
		
		
		
		
	}

}