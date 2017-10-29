package ua.nenya.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.nenya.domain.Payment;
import ua.nenya.domain.Project;
import ua.nenya.dto.PaymentDTO;

@Mapper(componentModel = "spring", uses = {})
public interface PaymentMapper {

    @Mapping(target = "projectId", source = "project.id")
    PaymentDTO paymentToPaymentDTO(Payment payment);

    @Mapping(target = "project", source = "projectId")
    Payment paymentDtoToPayment(PaymentDTO paymentDTO);

    default Project projectFromId(Long id) {
        if (id == null) {
            return null;
        }
        Project project = new Project();
        project.setId(id);
        return project;
    }

}
