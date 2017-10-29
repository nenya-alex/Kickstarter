(function() {
    'use strict';

    angular
        .module('kickApp')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$state', 'Auth', 'Principal', 'ProfileService', 'LoginService'];

    function NavbarController ($state, Auth, Principal, ProfileService, LoginService) {
        var vm = this;

        vm.isSidebarCollapsed = true;
        vm.isAuthenticated = Principal.isAuthenticated;

        ProfileService.getProfileInfo().then(function(response) {
            vm.inProduction = response.inProduction;
            vm.swaggerEnabled = response.swaggerEnabled;
        });

        vm.login = login;
        vm.logout = logout;
        vm.toggleSidebar = toggleSidebar;
        vm.collapseSidebar = collapseSidebar;
        vm.$state = $state;

        function login() {
            collapseSidebar();
            LoginService.open();
        }

        function logout() {
            collapseSidebar();
            Auth.logout();
            $state.go('home');
        }

        function toggleNavbar() {
            vm.isSidebarCollapsed = !vm.isSidebarCollapsed;
        }

        function collapseNavbar() {
            vm.isSidebarCollapsed = true;
        }
    }
})();
