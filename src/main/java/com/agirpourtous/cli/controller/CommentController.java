package com.agirpourtous.cli.controller;

import com.agirpourtous.cli.menus.CommentMenu;
import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.api.requests.AddCommentRequest;
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

        try {
            Comment comment = client.getCommentService().findById(idComment).block();
            show.showComment(comment);
        }catch (Exception e){
            System.out.println("There is no Comment with the given id");
        }

        new CommentMenu(client);
    }

    public void updateComment(APIClient client) {
        String idComment = null;
        String idTicket = null;
        String userId = null;
        String text = null;

        System.out.println("Insert id of the Comment you want to update : ");

        while (idComment == null) {
            idComment = SCANNER.next();
        }

        try {

            Comment comment = client.getCommentService().findById(idComment).block();

            if (comment != null){

                System.out.println("Insert new ticket id (enter to keep " + comment.getTicketId() +" ) : ");
                idTicket = SCANNER.next();
                if (idTicket == null){
                    idTicket = comment.getTicketId();
                }

                System.out.println("Insert new User id (enter to keep " + comment.getUserId() +" ) : ");
                userId = SCANNER.next();
                if (userId == null){
                    userId = comment.getUserId();
                }

                System.out.println("Insert new text (enter to keep : \n" + comment.getText() +" \n) : ");
                text = SCANNER.next();
                if (text == null){
                    text = comment.getText();
                }

                AddCommentRequest addCommentRequest = new AddCommentRequest(idTicket,userId,text);
                try {
                    client.getCommentService().update(idComment, addCommentRequest).block();
                }catch (Exception e){
                    System.out.println("An Error occur whil updating comment ");
                }
            }
        }catch (Exception e){
            System.out.println("There is no comment with the given id");
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
        }catch (Exception e){
            System.out.println("There is no comment with the given id");
        }

        new CommentMenu(client);
    }
}
