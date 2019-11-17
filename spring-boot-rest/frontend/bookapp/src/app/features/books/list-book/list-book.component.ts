import { BooksService } from './../../../core/services/books.service';
import { Book } from './../../../shared/models/book.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-book',
  templateUrl: './list-book.component.html',
  styleUrls: ['./list-book.component.scss']
})
export class ListBookComponent implements OnInit {

  public books: Book[];

  constructor(private bookService: BooksService) { }

  ngOnInit() {
    this.fetchBooks();
  }

  private fetchBooks() {
    this.bookService.getAll() // observable Book[]
      .subscribe((books: Book[]) => {
        this.books = books;
        books.forEach(book => {
          console.log(book);
        });
      });
  }

}
