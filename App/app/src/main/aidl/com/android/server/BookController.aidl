// BookController.aidl
package com.android.server;

import com.android.server.Book;

interface BookController {

        List<Book> getBookList();

        void addBookInOut(inout Book book);

        void addBookIn(in Book book);

        void addBookOut(out Book book);
}
