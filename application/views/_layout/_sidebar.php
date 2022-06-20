<aside class="main-sidebar">
  <!-- sidebar: style can be found in sidebar.less -->
  <section class="sidebar">

    <!-- Sidebar user panel (optional) -->
    <div class="user-panel">
      <div class="pull-left image">
        <img src="<?php echo base_url(); ?>assets/img/<?php echo $userdata->foto; ?>" class="img-circle" alt="User Image">
      </div>
      <div class="pull-left info">
        <p><?php echo $userdata->nama; ?></p>
        <!-- Status -->
        <a href="<?php echo base_url(); ?>assets/#"><i class="fa fa-circle text-success"></i> Online</a>
      </div>
    </div>

    <!-- Sidebar Menu -->
    <ul class="sidebar-menu">
      <li class="header">LIST MENU</li>
      <!-- Optionally, you can add icons to the links -->

      <li <?php if ($page == 'home') {echo 'class="active"';}
                 ?>>
        <a href="<?php echo base_url('Home'); ?>">
          <i class="fa fa-home"></i>
          <span>Home</span>
        </a>
      </li>
      
      <li <?php if ($page == 'pegawai') {echo 'class="active"';} 
      if ($this->session->userdata('status') == "Logged in Librarian") {
        echo " style='display: none';";
      }?>>
        <a href="<?php echo base_url('Pegawai'); ?>">
          <i class="fa fa-user"></i>
          <span>Data Pegawai</span>
        </a>
      </li>

      <li <?php if ($page == 'buku') {echo 'class="active"';} ?>>
        <a href="<?php echo base_url('Buku'); ?>">
          <i class="fa fa-book"></i>
          <span>Data Buku</span>
        </a>
      </li>

      <li <?php if ($page == 'member') {echo 'class="active"';} ?>>
        <a href="<?php echo base_url('Member'); ?>">
          <i class="fa fa-user"></i>
          <span>Data Member</span>
        </a>
      </li>
    </ul>
    <!-- /.sidebar-menu -->
  </section>
  <!-- /.sidebar -->
</aside>