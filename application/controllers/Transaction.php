<?php
defined('BASEPATH') or exit('No direct script access allowed');

require(APPPATH . '/libraries/RestController.php');
require(APPPATH . '/libraries/Format.php');

use chriskacerguis\RestServer\RestController;

class Transaction extends RestController
{
    public function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model('M_member');
        $this->load->database();
    }
    public function index_get()
    {
        $id_member = $this->get('id_member');
        $borrows = $this->db->select('transaction.id_member, book.name, transaction.date_return, transaction.book_path')->from('transaction')
        ->join('book', 'book.id_book=transaction.id_book')
        ->where('id_member', $id_member)->get()->result();
        $this->response(array("result" => $borrows, 200));
    }
    public function index_post()
    {
        $id_book = $this->post('id_book');
        $id_member = $this->post('id_member');
        $borrow_date = $this->post('date_borrow');
        $return_date = $this->post('date_return');
        $book_path = $this->post('book_path');

        $response = $this->M_member->borrow($id_book,$id_member,$borrow_date,$return_date,$book_path);
        $this->response($response);
        

    }


}