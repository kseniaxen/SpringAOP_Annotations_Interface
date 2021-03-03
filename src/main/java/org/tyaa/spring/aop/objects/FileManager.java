package org.tyaa.spring.aop.objects;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.tyaa.spring.aop.annotations.ShowResult;
import org.tyaa.spring.aop.annotations.ShowTime;

@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class FileManager implements Manager {

	@Override
	@ShowTime
	@ShowResult
	public Set<String> getExtensionList(String folder) {
		//создаем объект File для текущего каталога
		File dir = new File(folder);
		//создание коллекции String с неповторяющимися элементами и
		//образует пустое дерево, которое будет сортировать элементы в порядке возрастания
		Set<String> extList = new TreeSet<>();
		//запускается цикл по файлам в заданной директории
		for (File file : dir.listFiles()) {
			//получаем название файла
			String fileName = file.getName();
			//вычисляем индекс символа в строке
			// последнего вхождения точки в названии файла
			int i = fileName.lastIndexOf(".");
			//если это файл, а не каталог и индекс не равен -1
			if (file.isFile() && i != -1) {
				//в коллекцию добавляется подстрока с названия файла, начиная со следующего символа после точки
				// и переходит в нижний регистр
				extList.add(fileName.substring(i + 1).toLowerCase());
			}
		}
		//возвращается полученная коллекция
		return extList;
	}

	@Override
	@ShowResult
	public Map<String, Integer> getExtensionCount(String folder) {
		//создаем объект File для текущего каталога
		File dir = new File(folder);
		//создаем коллекцию HashMap (ключ-значение) строка-целое, в котором ключ всегда является уникальным
		Map<String, Integer> map = new HashMap<>();

		//запускаем цикл по коллекции getExtensionList с неповторяющимися элементами
		for (String ext : getExtensionList(folder)) {
			//вызываем экземпляр реализованного интерфейса FilenameFilter (для фильтрации имен файлов в определенной папке)
			// и передаем строку в коллекции
			FilenameFilter filter = new CustomFileFilter(ext);
			//в коллекцию добавляем ключ - название расширения файла,
			//значение - количество файлов с определенным расширением в директории
			map.put(ext, dir.listFiles(filter).length);
		}
		//возвращаем полученную коллекцию
		return map;

	}

}
