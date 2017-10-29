(function() {
    'use strict';

    angular
        .module('kickApp')
        .controller('StoryTypeController', StoryTypeController);

    StoryTypeController.$inject = ['StoryType'];

    function StoryTypeController(StoryType) {

        var vm = this;

        vm.storyTypes = [];

        loadAll();

        function loadAll() {
            StoryType.query(function(result) {
                vm.storyTypes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
