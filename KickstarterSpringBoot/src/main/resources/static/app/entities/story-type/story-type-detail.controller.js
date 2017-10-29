(function() {
    'use strict';

    angular
        .module('kickApp')
        .controller('StoryTypeDetailController', StoryTypeDetailController);

    StoryTypeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'StoryType', 'Property'];

    function StoryTypeDetailController($scope, $rootScope, $stateParams, previousState, entity, StoryType, Property) {
        var vm = this;

        vm.storyType = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('kickApp:storyTypeUpdate', function(event, result) {
            vm.storyType = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
