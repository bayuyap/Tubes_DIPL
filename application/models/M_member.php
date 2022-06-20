<?php 
defined('BASEPATH') OR exit('No direct script access allowed');

class M_member extends CI_Model
{
  public function select_all() {
		$data = $this->db->get('member');

		return $data->result();
	}

  public function select_by_id($id) {
		$sql = "SELECT * FROM member WHERE id_member = '{$id}'";

		$data = $this->db->query($sql);

		return $data->row();
	}


    public function update($data) {
		$sql = "UPDATE member SET name='" .$data['name'] ."', username='" .$data['username'] ."', 
    password='" .md5($data['password'])."' WHERE id_member='" .$data['id'] ."'";

		$this->db->query($sql);

		return $this->db->affected_rows();
	}

	public function delete($id) {
		$sql = "DELETE FROM member WHERE id_member='" .$id ."'";

		$this->db->query($sql);

		return $this->db->affected_rows();
	}

  public function borrow($id_book, $id_member, $date_borrow, $date_return, $book_path)
  {
      $data = array(
          
          'id_member' => $id_member,
          'id_book' => $id_book,
          'date_borrow' => $date_borrow,
          'date_return' => $date_return,
          'book_path' => $book_path
      );

      $insert = $this->db->insert("transaction", $data);
      if($insert){
          $response['status']=200;
          $response['error']=false;
          $response['message']='Data Transaksi ditambahkan.';
          return $response;
        }else{
          $response['status']=502;
          $response['error']=true;
          $response['message']='Data Transaksi gagal ditambahkan.';
          return $response;
        }

        return $this->db->affected_rows();
      }

     
}




