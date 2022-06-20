<?php
defined('BASEPATH') or exit('No direct script access allowed');

require(APPPATH . '/libraries/RestController.php');
require(APPPATH . '/libraries/Format.php');

use chriskacerguis\RestServer\RestController;

class Member_Buku extends RestController
{

    public function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model('M_member');
        $this->load->database();
    }

    public function index_get()
    {
        $id = $this->get('id_book');
        $genre = $this->get('genre');
        if (!empty($id)) {
            $books = $this->db->select('id_book,name,synopsis,foto')->from('book')->where('id_book', $id)->get()->row();
            $this->response($books, 200);
        } else if (!empty($genre)) {
            $books = $this->db->where('genre', $genre)->get('book')->result();
            $this->response(array("result" => $books, 200));
        }else {
            $this->db->order_by('id_book', 'RANDOM');
            $this->db->limit(6);
            $books = $this->db->get('book')->result();
            $this->response(array("result" => $books, 200));
        }

    }




}
