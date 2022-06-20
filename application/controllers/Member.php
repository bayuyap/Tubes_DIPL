<?php
defined('BASEPATH') OR exit('No direct script access allowed');

 class Member extends AUTH_Controller 
 {
    public function __construct() {
		parent::__construct();
		$this->load->model('M_member');
		
	}

    public function index()
    {
      $data['userdata'] = $this->userdata;
      $data['dataMember'] = $this->M_member->select_all();
  
      $data['page'] = "Member";
      $data['judul'] = "Data Member";
      $data['deskripsi'] = "Manage Data Member";
  
      $this->template->views('member/home', $data);
    }
  
    public function tampil()
    {
      $data['dataMember'] = $this->M_member->select_all();
      $this->load->view('member/list_data', $data);
    }

	public function update()
	{
	  $id = trim($_POST['id']);
  
	  $data['dataMember'] = $this->M_member->select_by_id($id);
	  $data['userdata'] = $this->userdata;
  
	  echo show_my_modal('modals/modal_update_member', 'update-member', $data);
	}

    public function prosesUpdate() {
		$this->form_validation->set_rules('name', 'Nama', 'trim|required');
        $this->form_validation->set_rules('username', 'Username', 'trim|required');
        $this->form_validation->set_rules('password', 'Password', 'trim|required');
		$data 	= $this->input->post();
		if ($this->form_validation->run() == TRUE) {
			$result = $this->M_member->update($data);

			if ($result > 0) {
				$out['status'] = '';
				$out['msg'] = show_succ_msg('Data Member Berhasil diupdate', '20px');
			} else {
				$out['status'] = '';
				$out['msg'] = show_succ_msg('Data Member Gagal diupdate', '20px');
			}
		} else {
			$out['status'] = 'form';
			$out['msg'] = show_err_msg(validation_errors());
		}

		echo json_encode($out);
	}

	public function delete()
	{
	  $id = $_POST['id'];
	  $result = $this->M_member->delete($id);
  
	  if ($result > 0) {
		echo show_succ_msg('Data Member Berhasil dihapus', '20px');
	  } else {
		echo show_err_msg('Data Member Gagal dihapus', '20px');
	  }
	}
 }
 
?>