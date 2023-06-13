function deleteStudent(sid) {
  //Check if user want to delete a student, if yes then send request to the backend
  if (confirm("Are you sure you want to delete this student?")) {
      $.ajax({
          type: 'POST',
          url: '/students/delete',
          data: { id: sid },
          success: function() {
            location.reload();
          }
      });
  }
}