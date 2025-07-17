package operation;

import model.Container;
import java.util.List;

public interface ContainerOperations {
    boolean insertContainer(int sellerId, double length, double width, double height);
    boolean updateContainerLength(int containerId, double length);
    boolean updateContainerWidth(int containerId, double width);
    boolean updateContainerHeight(int containerId, double height);
    boolean deleteContainer(int containerId);
    boolean deleteSeller(int sellerId);
    List<Container> getContainersBySeller(int sellerId);
}
