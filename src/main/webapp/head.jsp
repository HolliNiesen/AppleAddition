<%--
  Created by IntelliJ IDEA.
  User: Holli
  Date: 7/25/2017
  Time: 10:24 AM
--%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>${title}</title>

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.2.1.js" integrity=""   crossorigin="anonymous"></script>
    <script>
        $(document).ready(function(){
            $("img.gameImage").animate({height: '+=150px'}, "slow");
        });
        $(document).ready(function(){
            $("img.gameImage").animate({height: '-=150px'}, "slow");
        });
    </script>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="" crossorigin="anonymous"></script>

    <!-- The next two items are to support jQuery datatables. Learn more about datatables here:https://datatables.net -->
    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.13/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.13/js/jquery.dataTables.js"></script>

    <!-- Custom stylesheet -->
    <link rel="stylesheet" type="text/css" href="css/styles.css">

    <!-- JavaScript for form validation -->
    <script type="text/javascript" src="javascript/validateForm.js"></script>

</head>