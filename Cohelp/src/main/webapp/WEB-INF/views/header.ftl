<#import "spring.ftl" as spring />
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-warning">
        <a class="navbar-brand" href="#"><img width="270px" height="80px" src="/resources/img/var2.png"
                                              class="img-responsive" alt="Blockchain"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#"><@spring.message "header.page.about"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/volunteers"><@spring.message "header.page.volunteers"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/tasks"><@spring.message "header.page.events"/></a>
                </li>
            </ul>
            <@security.authorize access="! isAuthenticated()">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/signIn"><@spring.message "header.page.signIn"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/signUp"><@spring.message "header.page.signUp"/></a>
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
