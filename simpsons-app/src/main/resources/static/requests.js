GET: $(document).ready(
 function() {
  getSimpsons();
 })

function getSimpsons() {
 $.ajax({
  type: "GET",
  url: "api/character",
  success: function(result) {

   $('#table').bootstrapTable('showLoading');
   $('#table').bootstrapTable({
    data: result
   });

   $('#table').bootstrapTable('hideLoading');
  },
  error: function(e) {
   console.log("ERROR: ", e);
  }
 });
}

function deleteSimpson(id) {
 $.ajax({
  type: "DELETE",
  url: "api/character/" + id,
  success: function(result) {
   $('#table').bootstrapTable('remove', {
    field: 'id',
    values: id
   });
  },
  error: function(e) {
   console.log("ERROR: ", e);
  }
 });
}

function loadEditSimpsonModal(id) {
 $.ajax({
  type: "GET",
  url: "api/character/" + id,
  success: function(result) {

   loadSimpsonDataIntoModal(result)
   $('#editModal').modal('show');

  },
  error: function(e) {
   console.log("ERROR: ", e);
  }
 });
}


function updateSimpson() {

 id = $('#idEdited').val();
 firstName = $('#addFirstName').val();
 lastName = $('#addLastName').val();
 age = $('#addAge').val();
 picture = $('#addPicture').val();

 if (firstName == "" || lastName == "" || age == "" || picture == "") {
  alert("All the fields are mandatory");
  return false;
 }

 $.ajax({
  type: "PUT",
  url: "api/character/" + id,
  contentType: 'application/json',
  data: JSON.stringify({
   "firstName": firstName,
   "lastName": lastName,
   "age": age,
   "picture": picture,
  }),
  success: function(result) {

   $('#editModal').modal('hide');
   //For wait 5 seconds
   setTimeout(function() {
    location.reload(); //Refresh page
   }, 500);

  },
  error: function(e) {
   console.log("ERROR: ", e);
  }
 });

}

function addSimpson() {

 firstName = $('#newFirstName').val();
 lastName = $('#newLastName').val();
 age = $('#newAge').val();
 picture = $('#newPicture').val();

 if (firstName == "" || lastName == "" || age == "" || picture == "") {
  alert("All the fields are mandatory");
  return false;
 }

 $.ajax({
  type: "POST",
  url: "api/character",
  contentType: 'application/json',
  data: JSON.stringify({
   "firstName": firstName,
   "lastName": lastName,
   "age": age,
   "picture": picture,
  }),
  success: function(result) {
   $('#addModal').modal('hide');
   clearNewSimpsonModal()
   //For wait 5 seconds
   setTimeout(function() {
    location.reload(); //Refresh page
   }, 500);
  },
  error: function(e) {
   console.log("ERROR: ", e);
  }
 });

}


function loadSimpsonDataIntoModal(result) {

 $('#addFirstName').val(result.firstName);
 $('#addLastName').val(result.lastName);
 $('#addAge').val(result.age);
 $('#addPicture').val(result.picture);
 $('#idEdited').val(result.id);

}

function clearNewSimpsonModal() {

 $('#newFirstName').val("");
 $('#newLastName').val("");
 $('#newAge').val("");
 $('#newPicture').val("");

}

function imageFormatter(value, row) {
 return '<img src="' + value + ' "  width = "80px" height = "100px" />';
}

function editFormatter(value, row) {
 return '<a class="btn btn-warning" onclick="loadEditSimpsonModal(\'' + value + '\')"> <i class="fas fa-user-edit ml-2"></i></a>';
}

function deleteFormatter(value, row) {
 return '<a class="btn btn-warning" onclick="deleteSimpson(\'' + value + '\')"> <i class="fas fa-user-times ml-2"></i></a>';
}