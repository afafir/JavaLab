

<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-warning">
        <a class="navbar-brand" href="#"><img width="270px" height="80px" src="/resources/img/var2.png" class="img-responsive" alt="Blockchain"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item">
              <a class="nav-link" href="#"> О проекте </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/volunteers"> Наши волонтеры</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/tasks"> Мероприятия</a>
            </li>
          </ul>
            <@security.authorize access="! isAuthenticated()">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="/signIn">Вход</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/signUp">Регистрация</a>
            </li>
              </@security.authorize>
              <@security.authorize access=" isAuthenticated()">
              <ul class="navbar-nav ml-auto">
                  <li class="nav-item">
                      <a class="nav-link" href="/profile">Профиль</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="/logout">Выход</a>
                  </li>
                  </@security.authorize>
        </ul>
        </div>
      </nav>
</header>
