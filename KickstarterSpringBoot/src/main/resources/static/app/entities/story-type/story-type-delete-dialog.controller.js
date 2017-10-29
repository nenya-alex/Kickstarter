(function() {
    'use strict';

    angular
        .module('kickApp')
        .controller('StoryTypeDeleteController',StoryTypeDeleteController);

    StoryTypeDeleteController.$inject = ['$uibModalInstance', 'entity', 'StoryType'];

    function StoryTypeDeleteController($uibModalInstance, entity, StoryType) {
        var vm = this;

        vm.storyType = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            StoryType.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
