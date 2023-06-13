function deleteStudent(sid) {
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
function getColorCode(hairColor) {
  switch (hairColor) {
    case 'Blonde':
      return 'yellow';
    default:
      return hairColor;
  }
}