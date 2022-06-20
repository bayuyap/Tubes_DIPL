<?php
$no = 1;
  foreach ($dataMember as $member) {
    ?>
    <tr>
    <td><?php echo $no; ?></td>
      <td style="min-width:230px;"><?php echo $member->name;?></td>
      <td><?php echo $member->username; ?></td>
      <td><?php echo $member->password; ?></td>
      <td class="text-center" style="min-width:230px;">
        <button class="btn btn-warning update-dataMember" data-id="<?php echo $member->id_member; ?>"><i class="glyphicon glyphicon-repeat"></i> Update</button>
        <button class="btn btn-danger konfirmasiHapus-Member" data-id="<?php echo $member->id_member; ?>" data-toggle="modal" data-target="#konfirmasiHapus"><i class="glyphicon glyphicon-remove-sign"></i> Delete</button>
      </td>
    </tr>
    <?php
    $no++;
  }
?>