<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>JavaLab</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <style>
        body {

            font-size: 48px;
        }
    </style>
</head>
<body>
<p>Dear ${name},</p>
<p>activation link: <a href=http://localhost:8080/EmailSendingRegister_war/confirmation?token=${token}">tap here </a>
</p>
</body>
</html>