<#import "spring.ftl" as spring />
<#assign  security=JspTaglibs["/WEB-INF/security.tld"] />

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/bootstrap-grid.css">
    <link rel="stylesheet" href="resources/css/bootstrap-reboot.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/suggestions-jquery@20.2.3/dist/css/suggestions.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/suggestions-jquery@20.2.3/dist/js/jquery.suggestions.min.js"></script>


</head>
<body>
<#include "header.ftl">
<@spring.bind "signUpDto"/>
<main class="login-form my-ms-5">
    <div class="container my-sm-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header text-center"><h1><@spring.message 'signUp.page.register'/></h1></div>
                    <div class="card-body">
                        <form action="signUp" method="post" onsubmit="return Validate()">
                            <div class="form-group row">
                                <label for="name" class="col-md-4 col-form-label text-md-right"><@spring.message 'signUp.page.name'/></label>
                                <div class="col-md-6">
                                    <input type="text" id="name" class="form-control" name="name" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="surname" class="col-md-4 col-form-label text-md-right"><@spring.message 'signUp.page.surname'/>
                                </label>
                                <div class="col-md-6">
                                    <input type="text" id="surname" class="form-control" name="surname" required>
                                </div>
                            </div>


                            <div class="form-group row">
                                <label for="role" class="col-md-4 col-form-label text-md-right"><@spring.message 'signUp.page.role'/></label>
                                <div class="col-md-6">
                                    <select id="role" name="role" class="form-control" required>

                                        <option value="Волонтёр">Волонтёр</option>

                                        <option value="Мне нужна помощь">Мне нужна помощь</option>


                                    </select>
                                </div>
                            </div>


                            <div class="form-group row">
                                <label for="city" class="col-md-4 col-form-label text-md-right"><@spring.message 'signUp.page.city'/></label>
                                <div class="col-md-6">
                                    <input id="city" value="г Казань" name="city" type="text" disabled="true"
                                           class="form-control" required/></div>
                            </div>


                            <div class="form-group row">
                                <label for="street" class="col-md-4 col-form-label text-md-right"><@spring.message 'signUp.page.street'/></label>
                                <div class="col-md-6">
                                    <input id="street" name="street" type="text" class="form-control" required/></div>
                            </div>

                            <div class="form-group row">
                                <label for="house" class="col-md-4 col-form-label text-md-right"><@spring.message 'signUp.page.house'/></label>
                                <div class="col-md-6">
                                    <input id="house" name="house" type="text" class="form-control" required/></div>
                            </div>

                            <div class="form-group row">
                                <label for="tel" class="col-md-4 col-form-label text-md-right"><@spring.message 'signUp.page.tel'/></label>
                                <div class="col-md-6">
                                    <input type="tel" id="tel" class="form-control" name="tel"
                                           pattern="\+7\-[0-9]{3}\-[0-9]{3}\-[0-9]{2}\-[0-9]{2}" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="email_address" class="col-md-4 col-form-label text-md-right"><@spring.message 'signUp.page.email'/></label>
                                <div class="col-md-6">
                                    <@spring.formInput "signUpDto.email", "class=form-control"/>
                                    <@spring.showErrors "<br>"/>
                                  <!--  <input type="email" id="email" class="form-control" name="email" required> -->
                                </div>

                            </div>


                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right"><@spring.message 'signUp.page.password'/></label>
                                <div class="col-md-6">
                                    <@spring.formPasswordInput "signUpDto.password", "class=form-control"/>
                                    <@spring.showErrors "<br>"/>
                                </div>

                            </div>


                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-warning" >
                                    <@spring.message 'signUp.page.register'/>
                                </button>
                            </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        </form>

                    </div>

                </div>
            </div>
        </div>
    </div>
    </div>
</main>
<script src="resources/js/city.js"></script>
<script>
    /**
     * @return {boolean}
     */
    function Validate()
    {
        if (!/^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test($('#email').val()))
        {
            alert("Введите верный e-mail");
            return (false)
        }
        if ($('#password').val().length < 6) {
            alert("Пароль должен быть длиннее 6 символов");
            return (false)
        }
        return true;
    }
</script>

</body>
</html>