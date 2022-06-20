<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class M_buku extends CI_Model {
	public function select_all() {
		$data = $this->db->get('book');

		return $data->result();
	}

	public function select_by_id($id) {
		$sql = "SELECT * FROM book WHERE id_book = '{$id}'";

		$data = $this->db->query($sql);

		return $data->row();
	}

	public function insert($data) {
		$sql = "INSERT INTO `book`(`name`, `author`, `publisher`, `synopsis`, `genre`, `foto`, `pdf`) VALUES 
		('" .$data['name']."','" .$data['author']."','" .$data['publisher']."','".$data['synopsis']."','".$data['genre']."', 
		'".$data['foto']."', '".$data['pdf']."' )";
		$this->db->query($sql);

		return $this->db->affected_rows();
	}

	public function insert_batch($data) {
		$this->db->insert_batch('book', $data);
		
		return $this->db->affected_rows();
	}

	public function update($data) {
		$sql = "UPDATE book SET name='" .$data['name'] ."', author='" .$data['author'] ."', publisher='" .$data['publisher'] ."', 
        synopsis='" .$data['synopsis'] ."', genre='" .$data['genre'] ."', foto='" .$data['foto'] ."', pdf='" .$data['pdf'] ."' 
		WHERE id_book='" .$data['id'] ."'";

		$this->db->query($sql);

		return $this->db->affected_rows();
	}

	public function delete($id) {
		$sql = "DELETE FROM book WHERE id_book='" .$id ."'";

		$this->db->query($sql);

		return $this->db->affected_rows();
	}

	public function check_nama($name) {
		$this->db->where('name', $name);
		$data = $this->db->get('book');

		return $data->num_rows();
	}

	public function total_rows() {
		$data = $this->db->get('book');

		return $data->num_rows();
	}
}

/* End of file M_book.php */
/* Location: ./application/models/M_book.php */