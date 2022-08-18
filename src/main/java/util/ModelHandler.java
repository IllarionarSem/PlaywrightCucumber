package util;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import io.cucumber.datatable.DataTable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import model.Model;
import model.row.ModelInfo;
import model.row.BaseRow;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModelHandler {

    public static <T extends Model, Row extends BaseRow<T>> List<T> getModelList(DataTable dataTable, Class<Row> rowClass) {
        return dataTable.cells().stream().skip(1).map(list -> getModel(dataTable.cells().get(0), list, rowClass)).toList();
    }

    public static <T extends Model, Row extends BaseRow<T>> List<T> getModelList(Page page, String rowPath, String rowItemPath, Class<Row> rowClass) {
        return page.querySelectorAll(rowPath).stream().map(e -> getModel(e, rowItemPath, rowClass)).toList();
    }

    @SneakyThrows
    private static <T extends Model, Row extends BaseRow<T>> T getModel(List<String> header, List<String> row, Class<Row> rowClass) {
        Row rowModel = rowClass.getDeclaredConstructor().newInstance();
        Stream.of(rowModel.getClass().getDeclaredFields()).limit(row.size()).forEach(field -> {
            if (Objects.nonNull(field.getAnnotation(ModelInfo.class))) {
                int index = header.indexOf(field.getAnnotation(ModelInfo.class).name());
                setFieldValue(row.get(index), field, rowModel);
            } else {
                log.warn(String.format("The field '%s' is not annotated with ModelInfo annotation", field.getName()));
            }
        });
        return rowModel.asModel();
    }

    @SneakyThrows
    private static <T extends Model, Row extends BaseRow<T>> T getModel(ElementHandle element, String rowItemPath, Class<Row> rowClass) {
        Row rowModel = rowClass.getDeclaredConstructor().newInstance();
        Stream.of(rowModel.getClass().getDeclaredFields()).forEach(field -> {
            if (Objects.nonNull(field.getAnnotation(ModelInfo.class))) {
                String elementColumnName = field.getAnnotation(ModelInfo.class).name();
                ElementHandle itemElement = element.querySelector(String.format(rowItemPath, elementColumnName));
                if (Objects.isNull(itemElement)) {
                    log.warn(String.format("Can't parse element item %s", elementColumnName));
                    return;
                }
                setFieldValue(itemElement.innerText().trim(), field, rowModel);
            } else {
                log.warn(String.format("The field '%s' is not annotated with ModelInfo annotation", field.getName()));
            }
        });
        return rowModel.asModel();
    }

    private static <T extends Model, Row extends BaseRow<T>> void setFieldValue(String value, Field field, Row rowModel) {
        field.setAccessible(true);
        try {
            field.set(rowModel, value);
        } catch (IllegalAccessException e) {
            log.error("Illegal access");
        } catch (IllegalArgumentException e) {
            log.error(String.format("Can't parse the value '%s', to object of %s", value, field.getType().getSimpleName()));
        }
    }
}
