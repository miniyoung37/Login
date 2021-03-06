package service;

import repository.BookRepository;
import view.PageView;
import model.BookModel;


import java.util.List;

public class BookService {
    BookRepository bookRepository = new BookRepository();
    List<BookModel> Booklist = bookRepository.findbook();
    public int saveBook(String Writer, String BookName, String Isbn, String Amount, String State){

        for (BookModel Book : Booklist) {
            if(Writer.equals(Book.getWriter()) && BookName.equals(Book.getBookName()) && Isbn.equals(Book.getIsbn())
                ){
                return 0;               // 기존 책이 존재
            }
        }
        BookModel newBook = new BookModel(Writer, BookName, Isbn, Amount, State);
        bookRepository.saveBook(newBook);

        return 1;                   // 새로운 책 등록
    }

    public int enrollBook(String value){

        int result = 0;

        String Value = "Y";
        String nonValue = "N";

        if(Value.equalsIgnoreCase(value.toLowerCase())){
            return 1;
        }else if(nonValue.equalsIgnoreCase(value.toLowerCase())){
            return 2;
        }
        return  result;
    }


    public int search_writer(String writer){          // 책 검색
        List<BookModel> check_book = bookRepository.findbook();
        int result = 0;                 // 0 : 책 없음, 1 : 책 있음, 2 : 책 대여중

        String Writer = null;
        String Bookname = null;
        String Isbn = null;

        String caseWriter = writer.toLowerCase();
        String state = "On loan";
        for (BookModel booklist : check_book) {
            if(booklist.getWriter().toLowerCase().contains(caseWriter)){
                if(state.equals(booklist.getState())){
                    PageView.rentpage4();
                    result = 2;
                    return result;              // current rented
                }

                Writer = booklist.getWriter();
                Bookname = booklist.getBookName();
                Isbn = booklist.getIsbn();

                System.out.println("┌─────────────────────────────────────────────────────────────┐");
                System.out.println("│\t\t\t\t\t\t\t\t\t\t\tIt's a book in our library\t\t\t\t\t\t\t\t\t\t\t\t\t\t│");
                System.out.println("└─────────────────────────────────────────────────────────────┘");
                System.out.print("\t\t\t\t\tWriter : " + Writer);
                System.out.print("\t\t\tBook Name : " + Bookname);
                System.out.println("\t\t\tBook ISBN : " + Isbn);

                result = 1;
                return result;
            }
        }
        return result;
    }

    public int search_bookname(String bookname){          // 책 검색
        List<BookModel> check_book = bookRepository.findbook();
        int result = 0;                 // 0 : 책 없음, 1 : 책 있음, 2 : 책 대여중

        String Writer = null;
        String Bookname = null;
        String Isbn = null;

        String caseBookname = bookname.toLowerCase();
        String state = "On loan";
        for (BookModel booklist : check_book) {
            if(booklist.getBookName().toLowerCase().contains(caseBookname)){
                if(state.equals(booklist.getState())){
                    PageView.rentpage4();
                    result = 2;
                    return result;
                }

                Writer = booklist.getWriter();
                Bookname = booklist.getBookName();
                Isbn = booklist.getIsbn();

                System.out.println("┌─────────────────────────────────────────────────────────────┐");
                System.out.println("│\t\t\t\t\t\t\t\t\t\t\tIt's a book in our library\t\t\t\t\t\t\t\t\t\t\t\t\t\t│");
                System.out.println("└─────────────────────────────────────────────────────────────┘");
                System.out.print("\t\t\t\t\tWriter : " + Writer);
                System.out.print("\t\t\tBook Name : " + Bookname);
                System.out.println("\t\t\tBook ISBN : " + Isbn);

                result = 1;
                return result;
            }
        }
        return result;
    }


}

