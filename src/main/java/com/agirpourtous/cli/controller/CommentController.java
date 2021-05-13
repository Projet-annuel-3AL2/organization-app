package com.agirpourtous.cli.controller;

import com.agirpourtous.cli.menus.CommentMenu;
import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Comment;

import java.util.Scanner;

public class CommentController {
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static Show show = new Show();

    public void getAllComment(APIClient client) {
        System.out.println("List of all Comments :");
        client.getCommentService().findAll().subscribe(show::showComment);

        new CommentMenu(client);
    }

    public void findById(APIClient client) {
        System.out.println("Insert id of the Comment you want to display : ");

        String idComment = null;
        while (idComment == null) {
            idComment = SCANNER.next();
        }

        Comment comment = client.getCommentService().findById(idComment).block();
        show.showComment(comment);

        new CommentMenu(client);
    }

    public void updateComment(APIClient client) {
        String idComment = null;
        String idTicket = null;
        String UserId = null;
        String text = null;

        System.out.println("Insert id of the Comment you want to update : ");

        while (idComment == null) {
            idComment = SCANNER.next();
        }

        Comment comment = client.getCommentService().findById(idComment).block();

        if (comment != null){

            System.out.println("Insert new ticket id (enter to keep " + comment.getTicketId() +" ) : ");
            idTicket = SCANNER.next();
            if (idTicket == null){
                idTicket = comment.getTicketId();
            }

            System.out.println("Insert new User id (enter to keep " + comment.getUserId() +" ) : ");
            UserId = SCANNER.next();
            if (UserId == null){
                UserId = comment.getUserId();
            }

            System.out.println("Insert new text (enter to keep : \n" + comment.getText() +" \n) : ");
            text = SCANNER.next();
            if (text == null){
                text = comment.getText();
            }

            //TODO : create Comment with given var
            try {
                client.getCommentService().update(idComment, comment);
            }catch (Error error){
                System.out.println("An Error occur ");
            }
        }

        new CommentMenu(client);
    }

    public void deleteComment(APIClient client) {
        System.out.println("Insert id of the Comment you want to delete : ");

        String idComment = null;
        while (idComment == null) {
            idComment = SCANNER.next();
        }

        try {
            client.getCommentService().delete(idComment);
        }catch (Error error){
            System.out.println("An Error OCcur with");
        }

        new CommentMenu(client);
    }
}
